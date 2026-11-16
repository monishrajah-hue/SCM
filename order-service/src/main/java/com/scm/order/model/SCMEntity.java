package com.scm.order.model;

public sealed interface SCMEntity permits OrderRecord, ProductRecord {}
