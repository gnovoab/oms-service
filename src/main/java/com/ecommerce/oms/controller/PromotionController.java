
//Namespace
package com.ecommerce.oms.controller;

//Imports
import com.ecommerce.oms.domain.api.ApiErrorResponse;
import com.ecommerce.oms.domain.api.ApiMessageResponse;
import com.ecommerce.oms.domain.model.Promotion;
import com.ecommerce.oms.service.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Endpoint for Promotions
 */
@Tag(name = "PROMOTION", description = "Promotion controller")
@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {

    //Fields
    @Autowired
    private transient PromotionService promotionService;

    /**
     * Fetch promotions
     * @return
     */
    @Operation(summary = "Fetch all promotions", description = "All promotions", tags = { "promotion" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Promotion.class)))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Promotion>> fetchPromotions() {

        //Retrieve promotions
        Iterable<Promotion> promotions = promotionService.fetchPromotions();

        //Return the promotions
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }


    /**
     * Get specific promotion
     * @param id
     * @return
     */
    @Operation(summary = "Retrieve a promotion", description = "Get a promotion", tags = { "promotion" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Promotion> fetchPromotion(@PathVariable @Valid Long id) {

        //Retrieve a promotion
        Promotion promotion = promotionService.findPromotion(id);

        //Return the promotion requested
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }



    /**
     * Create promotion
     * @return
     */
    @Operation(summary = "Create a new promotion", description = "New promotion", tags = { "promotion" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resource created", content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Promotion> createPromotion(@RequestBody @Valid Promotion promotion) {

        //Create promotion
        Promotion promotionCreated = promotionService.save(promotion);

        //Return the promotion created
        return new ResponseEntity<>(promotionCreated, HttpStatus.CREATED);
    }


    /**
     * Update promotion
     * @return
     */
    @Operation(summary = "Update a promotion", description = "Update a promotion", tags = { "promotion" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Promotion.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable @Valid Long id, @RequestBody @Valid Promotion promotion) {

        //Assign Id
        promotion.setId(id);

        //Update promotion
        Promotion promotionUpdated = promotionService.save(promotion);

        //Return the promotion ypdated
        return new ResponseEntity<>(promotionUpdated, HttpStatus.OK);
    }


    /**
     * Delete promotion
     * @return
     */
    @Operation(summary = "Delete a promotion", description = "Delete a promotion", tags = { "promotion" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ApiMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> deletePromotion(@PathVariable @Valid Long id) {

        //Delete promotion
        promotionService.delete(id);

        //Return the message
        return new ResponseEntity<>(new ApiMessageResponse(HttpStatus.OK, "Promotion Deleted"), HttpStatus.OK);
    }
    
    
    
}
