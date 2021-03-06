/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package br.com.tcc.smartcontract;

import java.math.BigInteger;

import org.web3j.tx.gas.StaticGasProvider;

public class CustomGasProvider extends StaticGasProvider {
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(3_000_000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(41_000_000_000L);

    public CustomGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}
