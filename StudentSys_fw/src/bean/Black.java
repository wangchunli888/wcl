package bean;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Black entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="blacktable"
    ,catalog="login"
)

public class Black  implements java.io.Serializable {


    // Fields    

     @Override
	public String toString() {
		return "Black [id=" + id + ", username=" + username + ", includeDate="
				+ includeDate + ", removed=" + removed + "]";
	}

	private Integer id;
     private String username;
     private Date includeDate;
     private Integer removed;


    // Constructors

    /** default constructor */
    public Black() {
    }

    
    /** full constructor */
    public Black(String username, Date includeDate, Integer removed) {
        this.username = username;
        this.includeDate = includeDate;
        this.removed = removed;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="username", length=50)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="includeDate", length=19)

    public Date getIncludeDate() {
        return this.includeDate;
    }
    
    public void setIncludeDate(Date includeDate) {
        this.includeDate = includeDate;
    }
    
    @Column(name="removed")

    public Integer getRemoved() {
        return this.removed;
    }
    
    public void setRemoved(Integer removed) {
        this.removed = removed;
    }
   








}