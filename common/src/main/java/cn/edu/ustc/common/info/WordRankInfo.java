package cn.edu.ustc.common.info;

import cn.edu.ustc.common.ActiveRecord;

public class WordRankInfo extends ActiveRecord{
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	private int times;
	private String _id,py;
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
}
