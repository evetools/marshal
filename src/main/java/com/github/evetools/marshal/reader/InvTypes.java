package com.github.evetools.marshal.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.github.evetools.marshal.Reader;
import com.github.evetools.marshal.python.PyBase;
import com.github.evetools.marshal.python.PyDict;
import com.github.evetools.marshal.python.PyList;
import com.github.evetools.marshal.python.PyObject;
import com.github.evetools.marshal.python.PyObjectEx;
import com.github.evetools.marshal.python.PyDBRow;
import com.github.evetools.marshal.python.PyBuffer;
import com.github.evetools.marshal.python.PyTuple;
import java.io.InputStream;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 * 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public class InvTypes {

    private Reader reader;

    private long timestamp;

    public static class InvType {

        private short raceID;

        private int marketGroupID;

        private boolean published;

        private double mass;

        private String typeName;

        private double volume;

        private double capacity;

        private int portionSize;

        private int groupID;

        private int iconID;

        private int dataID;

        private int graphicID;

        private int soundID;

        private String description;

        private int typeID;

        private double radius;

        private short categoryID;

        private double chanceOfDuplicating;

        private short attributeID;

        private long basePrice;

        public short getRaceID() {
            return raceID;
        }

        public void setRaceID(short raceID) {
            this.raceID = raceID;
        }

        public int getMarketGroupID() {
            return marketGroupID;
        }

        public void setMarketGroupID(int marketGroupID) {
            this.marketGroupID = marketGroupID;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public double getMass() {
            return mass;
        }

        public void setMass(double mass) {
            this.mass = mass;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getCapacity() {
            return capacity;
        }

        public void setCapacity(double capacity) {
            this.capacity = capacity;
        }

        public int getPortionSize() {
            return portionSize;
        }

        public void setPortionSize(int portionSize) {
            this.portionSize = portionSize;
        }

        public int getGroupID() {
            return groupID;
        }

        public void setGroupID(int groupID) {
            this.groupID = groupID;
        }

        public int getIconID() {
            return iconID;
        }

        public void setIconID(int iconID) {
            this.iconID = iconID;
        }

        public int getDataID() {
            return dataID;
        }

        public void setDataID(int dataID) {
            this.dataID = dataID;
        }

        public int getGraphicID() {
            return graphicID;
        }

        public void setGraphicID(int graphicID) {
            this.graphicID = graphicID;
        }

        public int getSoundID() {
            return soundID;
        }

        public void setSoundID(int soundID) {
            this.soundID = soundID;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTypeID() {
            return typeID;
        }

        public void setTypeID(int typeID) {
            this.typeID = typeID;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public short getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(short categoryID) {
            this.categoryID = categoryID;
        }

        public double getChanceOfDuplicating() {
            return chanceOfDuplicating;
        }

        public void setChanceOfDuplicating(double chanceOfDuplicating) {
            this.chanceOfDuplicating = chanceOfDuplicating;
        }

        public short getAttributeID() {
            return attributeID;
        }

        public void setAttributeID(short attributeID) {
            this.attributeID = attributeID;
        }

        public long getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(long basePrice) {
            this.basePrice = basePrice;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + typeID;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            InvType other = (InvType) obj;
            if (typeID != other.typeID)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "InvType [raceID=" + raceID + ", marketGroupID="
                    + marketGroupID + ", published=" + published + ", mass="
                    + mass + ", typeName=" + typeName + ", volume=" + volume
                    + ", capacity=" + capacity + ", portionSize=" + portionSize
                    + ", groupID=" + groupID + ", iconID=" + iconID
                    + ", dataID=" + dataID + ", graphicID=" + graphicID
                    + ", soundID=" + soundID + ", description=" + description
                    + ", typeID=" + typeID + ", radius=" + radius
                    + ", categoryID=" + categoryID + ", chanceOfDuplicating="
                    + chanceOfDuplicating + ", attributeID=" + attributeID
                    + ", basePrice=" + basePrice + "]";
        }

    };

    private Collection<InvType> collection;

    public Reader getReader() {
        return reader;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Iterator<InvType> iterator() {
        return collection.iterator();
    }

    public InvTypes(File file) throws Exception {
        this.reader = new Reader(file);
    }

    public InvTypes(InputStream inputStream) throws Exception {
        this.reader = new Reader(inputStream);
    }

    public void read() throws Exception {

        PyBase PyBase = this.reader.read();

        PyTuple tuple1 = PyBase.asTuple();
        PyTuple tuple2 = null;
        PyObject object = null;
        PyObjectEx objectEx = null;
        PyBuffer string = null;

        if (!PyBase.isObjectEx()) {
            if (tuple1 == null) {
                throw new RuntimeException("Invalid element: "
                        + PyBase.getType());
            }

            string = tuple1.get(0).asBuffer();

            if (string == null) {
                throw new RuntimeException("Invalid element: "
                        + tuple1.get(0).getType());
            }

            if (!string.toString().equals("config.BulkData.types")) {
                throw new RuntimeException("Invalid element content: " + string
                        + " expeced: config.BulkData.types");
            }

            object = tuple1.get(1).asObject();

            if (object == null) {
                throw new RuntimeException("Invalid element: "
                        + tuple1.get(1).getType());
            }

            tuple2 = object.getContent().asTuple();

            if (tuple2 == null) {
                throw new RuntimeException("Invalid element: "
                        + object.getContent().getType());
            }

            tuple1 = tuple2.get(0).asTuple();

            if (tuple1 == null) {
                throw new RuntimeException("Invalid element: "
                        + tuple2.get(0).getType());
            }

            this.timestamp = PyBase.convertWindowsTime(tuple1.get(0).asLong()
                    .longValue());

            objectEx = tuple2.get(4).asObjectEx();
        } else {
            objectEx = PyBase.asObjectEx();
        }

        if (objectEx == null) {
            throw new RuntimeException("Invalid element: "
                    + tuple2.get(4).getType());
        }

        this.read(objectEx);
    }

    private void read(PyObjectEx object) throws Exception {

        if (object == null) {
            throw new IllegalArgumentException("invalid PyObjectEx");
        }

        PyList list = object.getList();

        this.collection = new ArrayList<InvTypes.InvType>();

        for (Iterator<PyBase> iterator = list.iterator(); iterator.hasNext();) {
            PyBase type = (PyBase) iterator.next();

            if (!type.isDBRow()) {
                throw new RuntimeException("Invalid element: " + type.getType());
            }

            this.read(type.asDBRow());
        }
    }

    private void read(PyDBRow object) throws Exception {

        if (object == null) {
            throw new IllegalArgumentException("Invalid PyDBRow");
        }

        InvType item = new InvType();

        item.setRaceID(object.get("raceID").asByte().byteValue());
        if (object.get("marketGroupID").isInt()) {
            item.setMarketGroupID(object.get("marketGroupID").asInt().intValue());
        } else {
            item.setMarketGroupID(object.get("marketGroupID").asShort()
                    .shortValue());
        }
        item.setPublished(object.get("published").asBool().booleanValue());
        item.setMass(object.get("mass").asDouble().doubleValue());
        item.setTypeName(object.get("typeName").asBuffer().toString());
        item.setVolume(object.get("volume").asDouble().doubleValue());
        item.setCapacity(object.get("capacity").asDouble().doubleValue());
        item.setPortionSize(object.get("portionSize").asInt().intValue());
        if (object.get("marketGroupID").isInt()) {
            item.setGroupID(object.get("groupID").asInt().intValue());
        } else {
            item.setGroupID(object.get("groupID").asShort().shortValue());
        }
        item.setIconID(object.get("iconID").asInt().intValue());
        item.setDataID(object.get("dataID").asInt().intValue());
        item.setGraphicID(object.get("graphicID").asInt().intValue());
        item.setSoundID(object.get("soundID").asInt().intValue());
        item.setDescription(object.get("description").asBuffer().toString());
        item.setTypeID(object.get("typeID").asInt().intValue());
        item.setRadius(object.get("radius").asDouble().doubleValue());
        if (object.get("categoryID") != null) {
            item.setCategoryID(object.get("categoryID").asShort().shortValue());
        }
        item.setChanceOfDuplicating(object.get("chanceOfDuplicating").asDouble()
                .doubleValue());
        if (object.get("attributeID") != null) {
            item.setAttributeID(object.get("attributeID").asShort().shortValue());
        }
        item.setBasePrice(object.get("basePrice").asLong().longValue());

        this.collection.add(item);
    }
}
