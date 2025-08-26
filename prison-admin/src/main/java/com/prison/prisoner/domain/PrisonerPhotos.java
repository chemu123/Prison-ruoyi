package com.prison.prisoner.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.prison.common.annotation.Excel;
import com.prison.common.core.domain.BaseEntity;

/**
 * 囚犯照片对象 prisoner_photos
 * 
 * @author che
 * @date 2024-07-02
 */
public class PrisonerPhotos extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 姓名 */
    @Excel(name = "姓名")
    private String firstName;

    /** 照片 */
    @Excel(name = "照片")
    private String inmateImg;

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getFirstName() 
    {
        return firstName;
    }
    public void setInmateImg(String inmateImg) 
    {
        this.inmateImg = inmateImg;
    }

    public String getInmateImg() 
    {
        return inmateImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("firstName", getFirstName())
            .append("inmateImg", getInmateImg())
            .toString();
    }
}
