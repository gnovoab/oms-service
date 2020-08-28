
//Namespace
package com.ecommerce.oms.controller;

//Imports
import com.ecommerce.oms.domain.api.ApiErrorResponse;
import com.ecommerce.oms.domain.api.ApiMessageResponse;
import com.ecommerce.oms.domain.model.Order;
import com.ecommerce.oms.service.OrderService;
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
 * Endpoint for Orders
 */
@Tag(name = "ORDER", description = "Order controller")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    //Fields
    @Autowired
    private transient OrderService orderService;

    /**
     * Fetch orders
     * @return
     */
    @Operation(summary = "Fetch all orders", description = "All orders", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Order>> fetchOrders() {

        //Retrieve orders
        Iterable<Order> orders = orderService.fetchOrders();

        //Return the orders
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    /**
     * Get specific order
     * @param id
     * @return
     */
    @Operation(summary = "Retrieve an order", description = "Get an order", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> fetchOrder(@PathVariable @Valid Long id) {

        //Retrieve order
        Order order = orderService.findOrder(id);

        //Return the order requested
        return new ResponseEntity<>(order, HttpStatus.OK);
    }



    /**
     * Create orders
     * @return
     */
    @Operation(summary = "Create a new orders", description = "New orders", tags = { "orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resource created", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order orders) {

        //Create order
        Order orderCreated = orderService.save(orders);

        //Return the order created
        return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);
    }

}
