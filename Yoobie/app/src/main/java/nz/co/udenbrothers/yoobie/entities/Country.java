package nz.co.udenbrothers.yoobie.entities;

import nz.co.udenbrothers.yoobie.abstractions.SqlEntity;

/**
 * Created by user on 30/01/2017.
 */

public class Country extends SqlEntity{
    public String callingCode;
    public int id;
    public String name;
}
