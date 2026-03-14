package dev.joaquincoronado.betterme.user.controller;

import dev.joaquincoronado.betterme.auth.model.AuthErrorResponse;
import dev.joaquincoronado.betterme.auth.model.BettermeAuth;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@ApiResponses( value = {
    @ApiResponse(
        responseCode = "401",
        description = "Requester not authenticated",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AuthErrorResponse.class)
        )
    ),
    @ApiResponse(
        responseCode = "403",
        description = "Requester have not permission",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AuthErrorResponse.class)
        )
    )
})
@SecurityRequirement(name = "bearer-authentication")
public interface IUserController {
    @Operation(summary = "Create a new user", description = "Create a new user on data base")
    BettermeUser createUser(BettermeUser user);

    @ApiResponse(
        responseCode = "318",
        description = "Returns the user with the given id",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = BettermeUser.class)
        )
    )
    BettermeUser getById(long userId);
    BettermeUser deleteUser(
        @Parameter(description = "Is the id of the user to be deleted")
        long userId
    );
    BettermeUser updateUser(BettermeAuth bettermeAuth,BettermeUser user);
}
