package com.example.demo.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Transaction(TransactionNature nature, User user) {}
