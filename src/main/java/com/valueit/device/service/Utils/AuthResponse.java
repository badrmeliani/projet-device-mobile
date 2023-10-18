package com.valueit.device.service.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String accessToken;
    //    private List<String> roles=new ArrayList<>();
    private  String role;
//   private List<String> permession = new ArrayList<>();
}
