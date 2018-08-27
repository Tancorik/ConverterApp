package com.example.tancorik.converterapp.presentation.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {

    @Attribute(name = "Date")
    private String mDate;

    @Attribute(name = "name")
    private String mName;

    @ElementList(name = "Valute", inline = true)
    private List<Valute> mValuteList;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Valute> getValuteList() {
        return mValuteList;
    }

    public void setValuteList(List<Valute> valuteList) {
        mValuteList = valuteList;
    }

    @Root(name = "Valute")
    public static class Valute {

        @Attribute(name = "ID")
        private String mId;

        @Element(name = "NumCode")
        private int mNumCode;

        @Element(name = "CharCode")
        private String mCharCode;

        @Element(name = "Nominal")
        private int mNominal;

        @Element(name = "Name")
        private String mName;

        @Element(name = "Value")
        private String mValue;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public int getNumCode() {
            return mNumCode;
        }

        public void setNumCode(int numCode) {
            mNumCode = numCode;
        }

        public String getCharCode() {
            return mCharCode;
        }

        public void setCharCode(String charCode) {
            mCharCode = charCode;
        }

        public int getNominal() {
            return mNominal;
        }

        public void setNominal(int nominal) {
            mNominal = nominal;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public float getValue() {
            mValue = mValue.replace(",", ".");
            return Float.parseFloat(mValue);
        }

        public void setValue(String value) {
            mValue = value;
        }
    }
}
