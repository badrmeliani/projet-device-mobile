package com.valueit.device.domaine;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class TokenVo {
    private String username;
    private String jwttoken;
    private List<String> roles=new ArrayList<>();

}
