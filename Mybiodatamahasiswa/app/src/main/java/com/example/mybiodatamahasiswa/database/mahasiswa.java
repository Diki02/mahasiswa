package com.example.mybiodatamahasiswa.database;

public class mahasiswa {
        public static final String TABLE_NAME = "mahasiswa";

        public static final String COLUMN_NIM= "nim";
        public static final String COLUMN_NAME= "nama";
        public static final String COLUMN_GENDER= "jenis_kelamin";
        public static final String COLUMN_JURUSAN= "jurusan";
        public static final String COLUMN_ADDRESS= "alamat";
        public static final String COLUMN_IMAGE= "foto ";
    private final int id;


    private int nim;
        private String nama;
        private String jenis_kelamin;
        private String jurusan;
        private String alamat;

        // Create table SQL query
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_NIM+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_NAME+ " TEXT,"
                        + COLUMN_GENDER+ " TEXT,"
                        + COLUMN_JURUSAN+ " TEXT,"
                        + COLUMN_ADDRESS +"TEXT,"
                        +  COLUMN_IMAGE + "TEXT" ;



        public mahasiswa(int id, String nama, String jenis_kelamin, String jurusan, String alamat) {
            this.id = id;
            this.nama= nama;
            this.jenis_kelamin= jenis_kelamin;
            this.jurusan= jurusan;
            this.alamat = alamat;
        }     public int getNim() {
            return nim;
        }

        public void setNim(int Nim) {
            this.nim= nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama= nama;
        }

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin= jenis_kelamin;
        }

        public String getJurusan() {
            return jurusan;
        }

        public void setJurusan(String jurusan) {
            this.jurusan= jurusan;
        }
        public String getAlamat() {
            return jurusan;
        }

        public void setAlamat (String alamat) {
            this.alamat=alamat;
        }

    }

