package com.lera.assistant.services;

import com.lera.assistant.exceptions.JobNotFoundException;
import com.lera.assistant.model.Job;
import com.lera.assistant.repositories.ClientRepository;
import com.lera.assistant.repositories.JobRepository;
import com.lera.assistant.repositories.PaymentRepository;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImp implements JobService {
    private JobRepository jobRepository;
    private ClientRepository clientRepository;
    private PaymentRepository paymentRepository;
    private Logger logger = Logger.getLogger(JobServiceImp.class);

    public JobServiceImp(JobRepository jobRepository, ClientRepository clientRepository,
                         PaymentRepository paymentRepository) {
        this.jobRepository = jobRepository;
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAllByOrderByCompletedAscJobIdDesc();
    }

    @Override
    public Job getJobById(Long jobId) throws JobNotFoundException {
        return jobRepository.findById(jobId).orElseThrow(() -> {
            String message = "Job with id " + jobId + " was not found";
            logger.warn(message);
            return new JobNotFoundException(message);
        });
    }

    @Override
    public Job addJob(Job job) {
        job.setClient(clientRepository.getOne(job.getClient().getClientId()));
        job.setPayment(paymentRepository.save(job.getPayment()));
        return jobRepository.save(job);
    }

    @Override
    public Job editJob(Job jobToModify) {
        Job job = jobRepository.getOne(jobToModify.getJobId());
        job.setPayment(paymentRepository.save(jobToModify.getPayment()));
        job.setTitle(jobToModify.getTitle());
        job.setCompleted(jobToModify.getCompleted());
        job.setPaid(jobToModify.getPaid());
        job.setClient(clientRepository.getOne(jobToModify.getClient().getClientId()));
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
