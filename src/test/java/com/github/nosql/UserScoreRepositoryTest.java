package com.github.nosql;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserScoreRepositoryTest extends BasePersistenceTest {

    @Test
    public void shouldSave() throws Exception {
        Key key = new Key(namespace, SET, "key1");
        Bin bin = new Bin("mybin", "myvalue");
        client.put(policy, key, bin);

        Record actual = client.get(policy, key);

        assertThat(actual.bins).hasSize(1);
        assertThat(actual.bins.get("mybin")).isEqualTo("myvalue");
    }

}