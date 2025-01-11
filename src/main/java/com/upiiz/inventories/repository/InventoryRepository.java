package com.upiiz.inventories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upiiz.inventories.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}