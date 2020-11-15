/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

    @Test
    public void paramNoDetailsError() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(is("You have given no details in your detail list.")));
    }

    @Test
    public void paramArgumentsWithOperationsMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing"));
    }

    @Test
    public void paramArgumentsWithOperationsAndExtensionsMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extensions")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing, and the extensions are Hypervisor, RAID-6"));
    }

    @Test
    public void paramArgumentsWithOperationsAndExtensionsAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extensions")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing, and the extensions are Hypervisor, RAID-6, and the memory is full"));
    }

    @Test
    public void paramArgumentsWithOperationsAndExtensionsSentByNoahMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Noah,?details=operations,extensions")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing, and the extensions are Hypervisor, RAID-6"));
    }

    @Test
    public void paramArgumentsWithOperationsAndExtensionsAndMemorySentByNoahMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Noah,?details=operations,extensions")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing, and the extensions are Hypervisor, RAID-6, and the memory is full"));
    }

    @Test
    public void paramArgumentsWithOperationsAndMemoryAndSentByNoachExtensions() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,memory?name=Noach")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the operations are processing, and memory is full"));
    }

    @Test
    public void paramArgumentsWithMemoryAndExtensionsSentByNoahMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Noah,?details=extensions,memory")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the extensions are Hypervisor, RAID-6, and the operations are processing"));
    }

    @Test
    public void paramArgumentsWithOperationsAndExtensionsAndOperationsAndMemorySentByNoahMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Noah,memory,operations,extensions,memory")).andDo(print())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and the memory is full, and the operations are processing, and the extensions are Hypervisor, RAID-6, and the memory is full"));
    }

    @Test
    public void paramWithBadArguments() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations,junkError")).andDo(print())
                .andDo(print())
                .andExpect(status().reason(is("You have invalid details in your detail list.")));
    }
}
