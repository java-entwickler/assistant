package com.lera.assistant.repositories;


import com.lera.assistant.model.Job;
import com.lera.assistant.model.statistics.ClientStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByOrderByCompletedAscJobIdDesc();

    @Query("select sum(j.payment.price) from Job j " +
            "where monthname(j.dateCreated) = :month and year(j.dateCreated) = :year")
    BigDecimal getMonthlySalary(@Param("month") String month, @Param("year") int year);

    @Query("select sum(j.payment.price) from Job j")
    BigDecimal getTotalSalaryEver();

    @Query("select sum(j.payment.price) from Job j where year(j.dateCreated) = :year")
    BigDecimal getYearlySalary(@Param("year") int year);

    @Query(value = "select avg(salary) as average_salary " +
            "    from (" +
            "            select sum(price) as salary " +
            "            from job " +
            "            inner join payment on job.payment_id = payment.payment_id " +
            "            where year(date_created) = ?1 " +
            "            group by monthname(date_created) " +
            "    ) jobs", nativeQuery = true)
    BigDecimal getAverageSalary(int year);

    @Query("select sum(j.payment.price) " +
            "from Job j " +
            "where monthname(j.dateCreated) = :month " +
            "and year(j.dateCreated) = :year " +
            "and j.client.clientId = :clientId")
    BigDecimal getMonthlySalaryByClient(String month, int year, long clientId);

    @Query("select sum(j.payment.price) " +
            "from Job j " +
            "where j.client.clientId = :clientId ")
    BigDecimal getTotalEverByClient(@Param("clientId") long clientId);

    @Query("select sum(j.payment.price) " +
            "from Job j " +
            "where j.client.clientId = :clientId " +
            "and year(j.dateCreated) = :year")
    BigDecimal getYearlySalaryByClient(@Param("clientId") long clientId, @Param("year") int year);

    @Query(value = "select avg(salary) as average_salary " +
            "    from (" +
            "            select sum(price) as salary " +
            "            from job " +
            "            inner join payment on job.payment_id = payment.payment_id " +
            "            where year(date_created) = ?1 " +
            "                and client_id = ?2" +
            "            group by monthname(date_created) " +
            "    ) jobs ",
            nativeQuery = true)
    BigDecimal getAverageSalaryByClient(int year, long clientId);

    @Query("select  " +
            "new com.lera.assistant.model.statistics.ClientStat(j.client.name, sum(j.payment.price)) " +
            "from Job j " +
            "group by j.client.clientId " +
            "order by sum(j.payment.price) desc")
    List<ClientStat> getAllClientsStat();

}
