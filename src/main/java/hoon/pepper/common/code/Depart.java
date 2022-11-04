package hoon.pepper.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Depart implements CodeInfo{
    Y("청년부"),
    U("대학부");

    private String description;
}
