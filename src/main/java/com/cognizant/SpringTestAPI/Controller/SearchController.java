package com.cognizant.SpringTestAPI.Controller;

import com.cognizant.SpringTestAPI.Model.Candidate;
import com.cognizant.SpringTestAPI.Service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<Candidate> searchCandidates(@RequestParam(name = "q", defaultValue = "") String query) {
        return searchService.search(query);
    }
}