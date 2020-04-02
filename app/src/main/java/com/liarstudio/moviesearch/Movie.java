package com.liarstudio.moviesearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Класс модели, в нашем случае - Фильм. Содержит все необходимые для отображения интерфейса поля
 * Аннотации SerializedName(name) используются для явного указания имени Gson-конвертеру
 * Реализует интерфейс Parcealable для удобной передачи фильмов через Fragment
 */
public class Movie implements Parcelable {

    // Поля и конструкторы
    private int id;
    private String title;
    @SerializedName("overview")
    private String description;
    String release_date;
    @SerializedName("poster_path")
    private String posterUrl;

    public Movie() {
    }
    public Movie(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        description = parcel.readString();
        release_date= parcel.readString();
        posterUrl = parcel.readString();
    }

    // Геттеры и сеттеры

    @SerializedName("overview")
    public String getDescription() {
        return description;
    }


    @SerializedName("overview")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getId() {
        return id;
    }


    /**
     * Сгенерированный IDE метод, переопределяющий стандартный equals на основе полей класса
     * @param o объект, с которым производится сравнение
     * @return результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (posterUrl != null ? !posterUrl.equals(movie.posterUrl) : movie.posterUrl != null)
            return false;
        if (!title.equals(movie.title)) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null)
            return false;
        return release_date != null ? release_date.equals(movie.release_date) : movie.release_date == null;

    }
    /**
     * Сгенерированный IDE метод, переопределяющий стандартный hashCode на основе полей класса
     * @return хеш-функция с минимальным числом коллизий
     */

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (posterUrl != null ? posterUrl.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Переопределение стандартного метода
     * @param parcel - объект Parcel, в который происходит запись контента
     * @param i - доп. флаг, указывающий, как объект должен быть записан в посылку. По умолчанию
     *          используется 0
     */

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(release_date);
        parcel.writeString(posterUrl);
    }

    /**
     * Экземпляр класса Creator, которй извлекает Movie и массивы Movie[] из Parcel.
     */
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

}
