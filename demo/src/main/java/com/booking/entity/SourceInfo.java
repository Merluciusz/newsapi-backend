package com.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SourceInfo {
    public String id;
    public String name;
    public String description;
    public String url;
    public String category;
    public String language;
    public String country;
}
