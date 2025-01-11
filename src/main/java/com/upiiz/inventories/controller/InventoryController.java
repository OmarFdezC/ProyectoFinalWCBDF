package com.upiiz.inventories.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.upiiz.inventories.entities.Inventory;
import com.upiiz.inventories.responses.CustomResponse;
import com.upiiz.inventories.services.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://proyectofinalwcbdf.onrender.com"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/inventory")
@Tag(
        name = "Inventory"
)
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<List<Inventory>>> getInventories() {
        List<Inventory> inventories = new ArrayList<>();
        Link allInventoriesLink = linkTo(InventoryController.class).withSelfRel();
        List<Link> links = List.of(allInventoriesLink);
        try {
            inventories = inventoryService.getAllInventories();
            if (!inventories.isEmpty()) {
                CustomResponse<List<Inventory>> response = new CustomResponse<>(1, "Inventarios encontrados", inventories, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Inventarios no encontrados", inventories, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Inventory>> response = new CustomResponse<>(500, "Error interno de servidor", inventories, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<Inventory>> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = null;
        CustomResponse<Inventory> response = null;
        Link allInventoriesLink = linkTo(InventoryController.class).withSelfRel();
        List<Link> links = List.of(allInventoriesLink);
        try {
            inventory = inventoryService.getInventoryById(id);
            if (inventory.isPresent()) {
                response = new CustomResponse<>(1, "Inventario encontrado", inventory.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Inventario no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomResponse<Inventory>> crearInventory(@RequestBody Inventory inventory) {
        Link allInventoriesLink = linkTo(InventoryController.class).withSelfRel();
        List<Link> links = List.of(allInventoriesLink);
        try {
            Inventory inventory1 = inventoryService.createInventory(inventory);
            if (inventory1 != null) {
                CustomResponse<Inventory> response = new CustomResponse<>(1, "Inventario creado", inventory1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Inventario no encontrado", inventory1, links));
            }
        } catch (Exception e) {
            CustomResponse<Inventory> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomResponse<Inventory>> updateInventory(@RequestBody Inventory inventory, @PathVariable Long id) {
        Link allInventoriesLink = linkTo(InventoryController.class).withSelfRel();
        List<Link> links = List.of(allInventoriesLink);
        try {
            inventory.setInventory_id(id);
            if (!inventoryService.getInventoryById(id).equals("")) {
                Inventory inventoryEntity = inventoryService.updateInventory(inventory);
                CustomResponse<Inventory> response = new CustomResponse<>(1, "Inventario actualizado", inventoryEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                CustomResponse<Inventory> response = new CustomResponse<>(0, "Inventario no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            CustomResponse<Inventory> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomResponse<Inventory>> deleteInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventoryEntity = null;
        CustomResponse<Inventory> response = null;
        Link allInventoriesLink = linkTo(InventoryController.class).withSelfRel();
        List<Link> links = List.of(allInventoriesLink);

        try {
            inventoryEntity = inventoryService.getInventoryById(id);
            if (inventoryEntity.isPresent()) {
                inventoryService.deleteInventory(id);
                response = new CustomResponse<>(1, "Inventario eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Inventario no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}