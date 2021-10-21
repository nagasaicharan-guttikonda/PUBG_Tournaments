package com.stackroute.favouriteservice.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="matches",uniqueConstraints=@UniqueConstraint(columnNames = {"id","user_id"}))
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mid")
	private int mId;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "titleId")
	private String titleId;
	
	@Column(name = "gameMode")
	private String gameMode;
	
	@Column(name = "mapName")
	private String mapName;
	
	@Column(name = "createdAt")
	private String createdAt;
	
	@Column(name = "user_id")
	private String userId;
	
	
	

	@Override
	public String toString() {
		return "Match [mId=" + mId + ", id=" + id + ", titleId=" + titleId + ", gameMode=" + gameMode + ", mapName="
				+ mapName + ", createdAt=" + createdAt + "]";
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Match() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	

	public Match(int mId, String id, String titleId, String gameMode, String mapName, String createdAt, String userId) {
		super();
		this.mId = mId;
		this.id = id;
		this.titleId = titleId;
		this.gameMode = gameMode;
		this.mapName = mapName;
		this.createdAt = createdAt;
		this.userId = userId;
	}

	
}
