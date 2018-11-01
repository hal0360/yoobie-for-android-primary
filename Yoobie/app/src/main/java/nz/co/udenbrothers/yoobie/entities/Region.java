package nz.co.udenbrothers.yoobie.entities;

import nz.co.udenbrothers.yoobie.abstractions.SqlEntity;



public class Region extends SqlEntity{
    public int id;
    public String name;
    public int countryId;
}
