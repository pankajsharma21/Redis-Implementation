package com.Redisimpl.Redis.Implementation.Repo;

import com.Redisimpl.Redis.Implementation.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}