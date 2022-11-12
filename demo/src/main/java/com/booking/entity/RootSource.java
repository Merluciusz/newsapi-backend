package com.booking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RootSource {
    public String status;
    @JsonProperty("sources")
    public ArrayList<SourceInfo> infoSources;
}
