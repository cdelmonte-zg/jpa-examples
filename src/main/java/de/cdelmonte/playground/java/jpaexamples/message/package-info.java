/**
 * Although it’s possible to place such global annotations in the source file of
 * a class (any class, really, at the top), we’d rather keep global metadata in
 * a separate file. Package-level annotations are a good choice; they’re in a
 * file called package-info.java in a particular package directory.
 */
// @org.hibernate.annotations.NamedQueries({
//         @org.hibernate.annotations.NamedQuery(
//             name = "findItemsOrderByName", 
//             query = "select i from Item i order by i.name asc"),
//         @org.hibernate.annotations.NamedQuery(
//             name = "findItemBuyNowPriceGreaterThan", 
//             comment = "Custom SQL comment",
//             query = "select i from Item i where i.buyNowPrice > :price", 
//             timeout = 60) // Seconds!
// })

package de.cdelmonte.playground.java.jpaexamples.message;
