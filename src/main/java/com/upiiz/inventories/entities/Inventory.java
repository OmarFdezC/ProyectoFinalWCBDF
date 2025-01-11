package com.upiiz.inventories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    private Long product_id;

    private Long quantity;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public void setInventoryId(Long id) {
        this.inventory_id = id;
    }
}