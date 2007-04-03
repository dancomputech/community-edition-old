/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */

package org.alfresco.repo.attributes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.Map.Entry;

/**
 * The base class of the implementation of Values.
 * @author britt
 */
public abstract class AttributeImpl implements Attribute 
{
    /**
     * The primary key.
     */
    private long fID;
    
    /**
     * Base constructor.
     */
    protected AttributeImpl()
    {
    }
    
    public void setId(long id)
    {
        fID = id;
    }
    
    public long getId()
    {
        return fID;
    }
    
    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#clear()
     */
    public void clear() 
    {
        throw new AtrributeMethodNotImplemented("Not ListValue or MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#entrySet()
     */
    public Set<Entry<String, Attribute>> entrySet() 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#get(java.lang.String)
     */
    public Attribute get(String key) 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getBlobValue()
     */
    public byte[] getBlobValue() 
    {
        throw new AtrributeMethodNotImplemented("Not BlobValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getDoubleValue()
     */
    public double getDoubleValue() 
    {
        throw new AtrributeMethodNotImplemented("Not DoubleValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getIntValue()
     */
    public int getIntValue() 
    {
        throw new AtrributeMethodNotImplemented("Not IntValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getLongValue()
     */
    public long getLongValue() 
    {
        throw new AtrributeMethodNotImplemented("Not LongValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getSerializableValue()
     */
    public Serializable getSerializableValue() 
    {
        throw new AtrributeMethodNotImplemented("Not SerializableValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getStringValue()
     */
    public String getStringValue() 
    {
        throw new AtrributeMethodNotImplemented("Not StringValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#keySet()
     */
    public Set<String> keySet() 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#put(java.lang.String, org.alfresco.repo.attributes.Value)
     */
    public void put(String key, Attribute value) 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#remove(java.lang.String)
     */
    public void remove(String key) 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");    
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setBlobValue(byte[])
     */
    public void setBlobValue(byte[] value) 
    {
        throw new AtrributeMethodNotImplemented("Not BlobValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setDoubleValue(double)
     */
    public void setDoubleValue(double value) 
    {
        throw new AtrributeMethodNotImplemented("Not DoubleValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setIntValue(int)
     */
    public void setIntValue(int value) 
    {
        throw new AtrributeMethodNotImplemented("Not IntValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setLongValue(long)
     */
    public void setLongValue(long value) 
    {
        throw new AtrributeMethodNotImplemented("Not LongValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setSerializableValue(java.io.Serializable)
     */
    public void setSerializableValue(Serializable value) 
    {
        throw new AtrributeMethodNotImplemented("Not SerializableValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setStringValue(java.lang.String)
     */
    public void setStringValue(String value) 
    {
        throw new AtrributeMethodNotImplemented("Not StringValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#values()
     */
    public Collection<Attribute> values() 
    {
        throw new AtrributeMethodNotImplemented("Not MapValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getBooleanValue()
     */
    public boolean getBooleanValue() 
    {
        throw new AtrributeMethodNotImplemented("Not BooleanValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getByteValue()
     */
    public byte getByteValue() 
    {
        throw new AtrributeMethodNotImplemented("Not ByteValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getFloatValue()
     */
    public float getFloatValue() 
    {
        throw new AtrributeMethodNotImplemented("Not FloatValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#getShortValue()
     */
    public short getShortValue() 
    {
        throw new AtrributeMethodNotImplemented("Not ShortValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setBooleanValue(boolean)
     */
    public void setBooleanValue(boolean value) 
    {
        throw new AtrributeMethodNotImplemented("Not BooleanValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setByteValue(byte)
     */
    public void setByteValue(byte value) 
    {
        throw new AtrributeMethodNotImplemented("Not ByteValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setFloatValue(float)
     */
    public void setFloatValue(float value) 
    {
        throw new AtrributeMethodNotImplemented("Not FloatValue");
    }

    /* (non-Javadoc)
     * @see org.alfresco.repo.attributes.Value#setShortValue(short)
     */
    public void setShortValue(short value) 
    {
        throw new AtrributeMethodNotImplemented("Not ShortValue");
    }
}
