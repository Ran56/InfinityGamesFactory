package com.infinity.gamesFactory.model;


import javax.persistence.*;

@Entity
@Table(name = "Files")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "original_s3Key")
    private String originalS3Key;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "uuid_s3key")
    private String uuidS3key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalS3Key() {
        return originalS3Key;
    }

    public void setOriginalS3Key(String originalS3Key) {
        this.originalS3Key = originalS3Key;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getUuidS3key() {
        return uuidS3key;
    }

    public void setUuidS3key(String uuidS3key) {
        this.uuidS3key = uuidS3key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
