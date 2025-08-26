package com.prison.prisoner.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.prison.common.annotation.Excel;
import com.prison.common.core.domain.BaseEntity;

/**
 * 囚犯信息对象 inmates
 * 
 * @author che
 * @date 2024-07-01
 */
public class Inmates extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 囚犯ID */
    @Excel(name = "囚犯ID")
    private Long inmateId;

    /** 照片 */
    @Excel(name = "照片")
    private String inmateImg;

    /** 姓名 */
    @Excel(name = "姓名")
    private String firstName;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfBirth;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 入狱日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入狱日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date admissionDate;

    /** 预计释放日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计释放日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 犯罪类型 */
    @Excel(name = "犯罪类型")
    private String crime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 牢房信息信息 */
    private List<Cells> cellsList;

    public void setInmateId(Long inmateId) 
    {
        this.inmateId = inmateId;
    }

    public Long getInmateId() 
    {
        return inmateId;
    }
    public void setInmateImg(String inmateImg) 
    {
        this.inmateImg = inmateImg;
    }

    public String getInmateImg() 
    {
        return inmateImg;
    }
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getFirstName() 
    {
        return firstName;
    }
    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() 
    {
        return dateOfBirth;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setAdmissionDate(Date admissionDate) 
    {
        this.admissionDate = admissionDate;
    }

    public Date getAdmissionDate() 
    {
        return admissionDate;
    }
    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() 
    {
        return releaseDate;
    }
    public void setCrime(String crime) 
    {
        this.crime = crime;
    }

    public String getCrime() 
    {
        return crime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<Cells> getCellsList()
    {
        return cellsList;
    }

    public void setCellsList(List<Cells> cellsList)
    {
        this.cellsList = cellsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inmateId", getInmateId())
            .append("inmateImg", getInmateImg())
            .append("firstName", getFirstName())
            .append("dateOfBirth", getDateOfBirth())
            .append("gender", getGender())
            .append("admissionDate", getAdmissionDate())
            .append("releaseDate", getReleaseDate())
            .append("crime", getCrime())
            .append("status", getStatus())
            .append("cellsList", getCellsList())
            .toString();
    }
}
