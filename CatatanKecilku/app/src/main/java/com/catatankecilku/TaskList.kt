package com.catatankecilku

import android.os.Parcel
import android.os.Parcelable
import java.util.TimerTask

class TaskList(val nama:String,val task: ArrayList<String> = ArrayList()):Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.createStringArrayList()!!

    )
    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nama)
        dest.writeStringList(task)
    }

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(source: Parcel): TaskList {
            return TaskList(source)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }

}
