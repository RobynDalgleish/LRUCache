package com.github.robyndalgleish.cache;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Test suite for testing LRU shtuffs")
public class LRUCacheTest {

    @Test
    @DisplayName("Inaugural test")
    void doesTheThing(){
        // Given: A cache (Map<K, V>) with a limit of 5 entries, and an array (ArrayList<K>(5)) keeping the order of these entries.
        // LRUCache.put(), if given a key and a value, will put key/value pairs into the map and the keys into the array.
        LRUCache cache = new LRUCache(3);
        assertThat(cache.map.isEmpty()).isTrue();
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        assertThat(cache.map.size()).isEqualTo(3);

        // When: We put another entry in.
        cache.put(4, 4);

        // Then: The oldest entry is replaced by the new one.
        assertThat(cache.newest.value).isEqualTo(4);
        assertThat(cache.oldest.value).isEqualTo(2);
        assertThat(cache.map.size()).isEqualTo(3);
    }

    // Check that get request refreshes queried entry
}