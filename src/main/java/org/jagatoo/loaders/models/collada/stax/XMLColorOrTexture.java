/**
 * Copyright (c) 2007-2009, JAGaToo Project Group all rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * Neither the name of the 'Xith3D Project Group' nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) A
 * RISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE
 */
package org.jagatoo.loaders.models.collada.stax;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jagatoo.logging.JAGTLog;

/**
 * A Color or Texture used in ShadingParameters.
 * Child of ShadingParameters (Constant, Lambert,
 * Phong, or Blinn shading)
 * 
 * @author Amos Wenger (aka BlueSky)
 * @author Joe LaFata (aka qbproger)
 */
public class XMLColorOrTexture
{
    
    public XMLColor4 color4 = null;
    public XMLTexture texture = null;
    
    private void checkVars( String name )
    {
        if ( color4 != null || texture != null )
        {
            JAGTLog.exception( this.getClass().getSimpleName(), " invalid ", name, " tag." );
        }
    }
    
    public void parse( XMLStreamReader parser, String endTag ) throws XMLStreamException
    {
        doParsing( parser, endTag );
        
        Location loc = parser.getLocation();
        if ( texture == null && color4 == null )
            JAGTLog.exception( loc.getLineNumber(), ":", loc.getColumnNumber(), " ", this.getClass().getSimpleName(), ": missing texture or color." );
    }
    
    private void doParsing( XMLStreamReader parser, String endTag ) throws XMLStreamException
    {
        for ( int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next() )
        {
            switch ( event )
            {
                case XMLStreamConstants.START_ELEMENT:
                {
                    String localName = parser.getLocalName();
                    if ( localName.equals( "color" ) )
                    {
                        checkVars( "color" );
                        color4 = new XMLColor4( StAXHelper.parseText( parser ) );
                    }
                    else if ( localName.equals( "texture" ) )
                    {
                        checkVars( "texture" );
                        
                        texture = new XMLTexture();
                        texture.parse( parser );
                    }
                    else
                    {
                        JAGTLog.exception( "Unsupported ", this.getClass().getSimpleName(), " Start tag: ", parser.getLocalName() );
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT:
                {
                    if ( parser.getLocalName().equals( endTag ) )
                        return;
                    break;
                }
            }
        }
    }
}
