package de.cdelmonte.playground.java.jpaexamples.picture.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Picture {

    public Picture() {
    }

    @Id
    @GeneratedValue
    Long id;

    /**
     * TOAST is a mechanism PostgreSQL uses to keep physical data rows from
     * exceeding the size of a data block (typically 8KB). Postgres does not support
     * physical rows that cross block boundaries, so the block size is a hard upper
     * limit on row size. To allow user tables to have rows wider than this, the
     * TOAST mechanism breaks up wide field values into smaller pieces, which are
     * stored "out of line" in a TOAST table associated with the user table.
     * 
     * Each table you create has its own associated (unique) TOAST table, which may
     * or may not ever end up being used, depending on the size of rows you insert.
     * All of this is transparent to the user, and enabled by default.
     * 
     * When a row that is to be stored is "too wide" (the threshold for that is 2KB
     * by default), the TOAST mechanism first attempts to compress any wide field
     * values. If that isn't enough to get the row under 2KB, it breaks up the wide
     * field values into chunks that get stored in the associated TOAST table. Each
     * original field value is replaced by a small pointer that shows where to find
     * this "out of line" data in the TOAST table. TOAST will attempt to squeeze the
     * user-table row down to 2KB in this way, but as long as it can get below 8KB,
     * that's good enough and the row can be stored successfully.
     * 
     * All standard Postgres data types that could possibly have values wider than
     * 2KB support being "TOASTed" in this way, and so do most potentially-wide
     * extension data types.
     * 
     * You can view the current TOAST options for a table by opening psql and
     * running
     * 
     * \d+ table_name
     * 
     *
     * bytea (short for “byte array”) is the “new way” for storing binary data in
     * PostgreSQL. It uses TOAST (The Oversized-Attribute Storage Technique, proudly
     * called “the best thing since sliced bread” by the PostgreSQL community) to
     * transparently store data out of line.
     * 
     * A bytea is stored directly in the database table and vanishes when you delete
     * the table row. No special maintenance is necessary.
     * 
     * The main disadvantages of bytea are:
     * 
     * 1) like all TOASTed data types, there is an absolute length limit of 1GB
     * 
     * 2) when you read or write a bytea, all data have to be stored in memory (no
     * streaming support)
     * 
     * 
     * If you choose bytea, you should be aware of how TOAST works:
     * 
     * for a new table row that would exceed 2000 bytes, variable length data types
     * are compressed, if possible
     * 
     * if the compressed data would still exceed 2000 bytes, PostgreSQL splits
     * variable length data types in chunks and stores them out of line in a special
     * “TOAST table”
     * 
     * Now for already compressed data, the first step is unnecessary and even
     * harmful. After compressing the data, PostgreSQL will realize that the
     * compressed data have actually grown (because PostgreSQL uses a fast
     * compression algorithm) and discard them. That is an unnecessary waste of CPU
     * time.
     * 
     * Moreover, if you retrieve only of a substring of a TOASTed value, PostgreSQL
     * still has to retrieve all chunks that are required to decompress the value.
     * 
     * Fortunately, PostgreSQL allows you to specify how TOAST should handle a
     * column. The default EXTENDED storage type works as described above. If we
     * choose EXTERNAL instead, values will be stored out of line, but not
     * compressed. This saves CPU time. It also allows operations that need only a
     * substring of the data to access only those chunks that contain the actual
     * data.
     * 
     * So you should always change the storage type for compressed binary data to
     * EXTERNAL. This also allows us to implement streaming, at least for read
     * operations, using the substr function (see below).
     * 
     * The bytea table that I use in this benchmark is defined like
     *  
     * CREATE TABLE bins (
     * id bigint PRIMARY KEY,
     * data bytea NOT NULL
     * );
     * 
     * ALTER TABLE bins ALTER COLUMN data SET STORAGE EXTERNAL;
     * 
     */
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageBlobByteA;

    /**
     * If you are using the varbinary/blob type or something like that, you would
     * have to use @Lob as in that case, the main table only contains this LOB
     * locator which is a long. The driver then knows when you ask for the value by
     * using getBlob that it has to execute some select get_lob(?) query to retrieve
     * the actual contents.
     */
    @Lob
    private Blob imageBlobLob;

    @Lob
    private Clob description;

}
