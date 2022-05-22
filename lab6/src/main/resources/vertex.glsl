#version 330

layout (location =0) in vec3 position1;
layout (location =1) in vec3 position2;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
uniform float progress;

void main()
{
    vec3 vertex = mix(position1, position2, progress);
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(vertex , 1.0);
}