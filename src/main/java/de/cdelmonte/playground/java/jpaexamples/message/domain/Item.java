package de.cdelmonte.playground.java.jpaexamples.message.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Annotations on classes only cover metadata that applies to that particular class. 
 */

/**
 * We prefer to prefix Hibernate annotations with the full
 * org.hibernate.annotations package name. Consider this good practice because
 * you can easily see what metadata for this class is from the JPA specification
 * and which is vendor-specific.
 */
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class Item {

    @NotNull
    @Size(min = 2, max = 100, message = "min length: 2; max length: 100")
    private String name;

    @Future
    private LocalDateTime auctionEnd;

    /**
     * JPA doesn’t manage persistent associations. If you want to manipulate an
     * association, you must write the same code you would write without Hibernate.
     * If an association is bidirectional, you must consider both sides of the
     * relationship. If you ever have problems understanding the behavior of
     * associations in JPA, just ask yourself, “What would I do without Hibernate?”
     * Hibernate doesn’t change the regular Java semantics.
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        if (bid == null) {
            throw new NullPointerException("Bid cannot be null");
        }
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an Item");
        }
        bids.add(bid);
        bid.setItem(this);
    }

    /**
     * @param bid
     */
    public void removeBid(Bid bid) {
        if (bid == null) {
            throw new NullPointerException("Bid cannot be null");
        }
        bids.remove(bid);
        bid.setItem(null);
    }

    /**
     * @return Set<Bid>
     */
    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

}
