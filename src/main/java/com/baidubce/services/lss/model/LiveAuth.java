/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.lss.model;

public class LiveAuth {
    private String accessKey = null;
    private String secretKey = null;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public LiveAuth withAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public LiveAuth withSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveAuth {\n");
        sb.append("    accessKey: ").append(accessKey).append("\n");
        sb.append("    secretKey: ").append(secretKey).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
