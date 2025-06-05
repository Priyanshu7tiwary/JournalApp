package com.journal.journal.controller;

import com.journal.journal.entity.JournalEntry;
import com.journal.journal.service.AdminService;
import com.journal.journal.service.JournalEntryService;
import com.journal.journal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "Operations for admin to manage journal entries and users")
public class admincontroller {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private AdminService adminService;

    @Operation(
            summary = "Get all journal entries by username",
            description = "Retrieves all journal entries for a specific user, based on the username."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved entries"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("get/{username}")
    public ResponseEntity<?> getById(
            @Parameter(description = "Username of the user whose journal entries are to be fetched")
            @PathVariable String username
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ResponseEntity<?>) journalEntryService.getAll(username);
    }
}
