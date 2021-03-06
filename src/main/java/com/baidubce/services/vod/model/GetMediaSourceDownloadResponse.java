/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;

/*
 * Gets the properties of specific media resource managed by VOD service.
 */
public class GetMediaSourceDownloadResponse extends AbstractBceResponse {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    private String sourceUrl;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GetMediaSourceDownloadResponse { \n");
        sb.append("  sourceUrl = ").append(sourceUrl).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}