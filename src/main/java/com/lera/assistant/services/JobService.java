package com.lera.assistant.services;

import com.lera.assistant.exceptions.JobNotFoundException;
import com.lera.assistant.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    Job getJobById(Long jobId) throws JobNotFoundException;

    Job addJob(Job job);

    Job editJob(Job jobToModify);

    void deleteJob(Long jobId);
}
