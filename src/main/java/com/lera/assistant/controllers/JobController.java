package com.lera.assistant.controllers;

import com.lera.assistant.exceptions.JobNotFoundException;
import com.lera.assistant.model.Job;
import com.lera.assistant.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        try {
            Job job = jobService.getJobById(jobId);
            return new ResponseEntity<>(job, HttpStatus.OK);
        } catch (JobNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public Job addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
    }

    @PutMapping("")
    public Job editJob(@RequestBody Job job) {
        return jobService.editJob(job);
    }
}
