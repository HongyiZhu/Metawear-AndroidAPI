/*
 * Copyright 2014-2015 MbientLab Inc. All rights reserved.
 *
 * IMPORTANT: Your use of this Software is limited to those specific rights granted under the terms of a software
 * license agreement between the user who downloaded the software, his/her employer (which must be your
 * employer) and MbientLab Inc, (the "License").  You may not use this Software unless you agree to abide by the
 * terms of the License which can be found at www.mbientlab.com/terms.  The License limits your use, and you
 * acknowledge, that the Software may be modified, copied, and distributed when used in conjunction with an
 * MbientLab Inc, product.  Other than for the foregoing purpose, you may not use, reproduce, copy, prepare
 * derivative works of, modify, distribute, perform, display or sell this Software and/or its documentation for any
 * purpose.
 *
 * YOU FURTHER ACKNOWLEDGE AND AGREE THAT THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, TITLE,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT SHALL MBIENTLAB OR ITS LICENSORS BE LIABLE OR
 * OBLIGATED UNDER CONTRACT, NEGLIGENCE, STRICT LIABILITY, CONTRIBUTION, BREACH OF WARRANTY, OR OTHER LEGAL EQUITABLE
 * THEORY ANY DIRECT OR INDIRECT DAMAGES OR EXPENSES INCLUDING BUT NOT LIMITED TO ANY INCIDENTAL, SPECIAL, INDIRECT,
 * PUNITIVE OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY,
 * SERVICES, OR ANY CLAIMS BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE THEREOF), OR OTHER SIMILAR COSTS.
 *
 * Should you have any questions regarding your right to use this Software, contact MbientLab via email:
 * hello@mbientlab.com.
 */

package com.mbientlab.metawear.module;

import com.mbientlab.metawear.AsyncOperation;
import com.mbientlab.metawear.MetaWearBoard;

/**
 * Configures Bluetooth LE advertisement settings
 * @author Eric Tsai
 */
public interface Settings extends MetaWearBoard.Module {
    /**
     * Configures advertisement settings
     * @return Editor object to configure settings
     */
    ConfigEditor configure();

    /**
     * Reads the current advertisement settings
     * @return Advertisement configuration object, available when the read is completed
     */
    AsyncOperation<AdvertisementConfig> readAdConfig();

    /**
     * Remove Bluetooth bond to the board on disconnect
     */
    void removeBond();
    /**
     * Keep the bond on disconnect
     */
    void keepBond();
    /**
     * Trigger the board to start advertising
     */
    void startAdvertisement();
    /**
     * Triggers the board to initiate the Bluetooth bonding process with the connected Android device
     */
    void initiateBonding();

    /**
     * Wrapper class encapsulating the advertisement configuration
     * @author Eric Tsai
     */
    interface AdvertisementConfig {
        /**
         * Retrieves the device's advertising name
         * @return Device name
         */
        String deviceName();

        /**
         * Retrieves the advertising interval
         * @return Advertising interval
         */
        int interval();

        /**
         * Retrieves the advertising timeout
         * @return Advertising timeout
         */
        short timeout();

        /**
         * Retrieves the advertising transmitting power
         * @return Advertising transmitting power
         */
        byte txPower();

        /**
         * Retrieves the scan response
         * @return Advertising packet
         */
        byte[] scanResponse();
    }

    /**
     * Interface for configuring the advertisement settings
     * @author Eric Tsai
     */
    interface ConfigEditor {
        /**
         * Sets the device's advertising name
         * @param name    Device name, max of 8 ASCII characters
         * @return Calling object
         */
        ConfigEditor setDeviceName(String name);

        /**
         * Sets advertising intervals
         * @param interval    Advertisement interval, between [0, 65535] milliseconds
         * @param timeout     Advertisement timeout, between [0, 180] seconds where 0 indicates no timeout
         * @return Calling object
         */
        ConfigEditor setAdInterval(short interval, byte timeout);

        /**
         * Sets advertising transmitting power.  If a non valid value is set, the nearest valid value will be used instead
         * @param power    Valid values are: 4, 0, -4, -8, -12, -16, -20, -30
         * @return Calling object
         */
        ConfigEditor setTxPower(byte power);

        /**
         * Set a custom scan response packet
         * @param response    Byte representation of the response
         * @return Calling object
         */
        ConfigEditor setScanResponse(byte[] response);

        /**
         * Writes the new settings to the board
         */
        void commit();
    }
}
