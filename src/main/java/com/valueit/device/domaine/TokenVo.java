package com.valueit.device.domaine;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {
    private String username;
    private String jwttoken;
//    private List<String> roles=new ArrayList<>();
    private  String role;
//   private List<String> permession = new ArrayList<>();
}
