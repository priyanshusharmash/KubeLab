package com.metaminds.kubelab.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/info")
public class InfoController {

    @Value("${POD_IP:unknown}")
    private String podIp;

    @Value("${POD_NAME:unknown}")
    private String podName;

    @GetMapping
    public ResponseEntity<Map<String,String>>  getIp() {
        Map<String,String> map = new HashMap<>();
        map.put("Pod name",podName);
        map.put("ip",podIp);
        return ResponseEntity.ok(map);
    }
}
