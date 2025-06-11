package com.cybersoft.capstone.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchGameRequest {
    private String title;
    private int numPage;
    private int pageSize;
}
