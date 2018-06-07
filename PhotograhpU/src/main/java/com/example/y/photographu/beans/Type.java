package com.example.y.photographu.beans;

import java.io.Serializable;

public class Type  implements Serializable{
    private String typeName;
    private String typeIntro;
    private int typePic;

    public Type(String typeName, String typeIntro, int typePic) {
        this.typeName = typeName;
        this.typeIntro = typeIntro;
        this.typePic = typePic;
    }
    public Type() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeIntro() {
        return typeIntro;
    }

    public void setTypeIntro(String typeIntro) {
        this.typeIntro = typeIntro;
    }

    public int getTypePic() {
        return typePic;
    }

    public void setTypePic(int typePic) {
        this.typePic = typePic;
    }


}
