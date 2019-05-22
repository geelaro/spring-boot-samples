package com.geelaro.jpa.domain.repository;

import com.geelaro.jpa.domain.entity.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpRepository extends JpaRepository<IpAddress, Long> {

    IpAddress findByIp(String ip);
}
