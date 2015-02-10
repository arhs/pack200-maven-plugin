package com.arhs.mojo.pack200.packing.options;

/**
 * The MIT License (MIT)
 * Copyright (c) 2015 ARHS Developments SA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * Modification time value.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-11-20
 */
public enum ModificationTime {
    /**
     * Preserve deflation hints observed in the input JAR.
     */
    KEEP,

    /**
     * The packer will attempt to determine the latest modification time, among all the available entries in the
     * original archive, or the latest modification time of all the available entries in that segment.
     * This single value will be transmitted as part of the segment and applied to all the entries in each segment.
     * This can marginally decrease the transmitted size of the archive at the expense of setting all installed
     * files to a single date.
     */
    LATEST
}