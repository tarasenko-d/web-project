package com.webproject.dto.response;

import com.webproject.model.Person;
import lombok.Data;

@Data
public class GetUserByIdResponse {
    private Person user;
}
