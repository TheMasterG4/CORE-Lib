//
// Created by Boris on 11/7/2026.
//

#include <math.h>
#include <stdint.h>
#include <jni.h>

static int tint_channel(int base, int tint, float strength) {
    float m = (float)(base * tint) / 255.0f;
    float v = (float)base + (m - (float)base) * strength;
    return (int) floorf(v + 0.5f);
}

JNIEXPORT jint JNICALL
Java_image_color_EasyColor_tintNative(JNIEnv *env, jclass cls,
                            jint baseArgb, jint tintArgb, jfloat strength)
{
    (void)env; (void)cls;

    if (strength < 0.0f) strength = 0.0f;
    if (strength > 1.0f) strength = 1.0f;

    uint32_t b = (uint32_t)baseArgb;
    uint32_t t = (uint32_t)tintArgb;

    int a = (int)((b >> 24) & 0xFF);
    int r = tint_channel((int)((b >> 16) & 0xFF), (int)((t >> 16) & 0xFF), strength);
    int g = tint_channel((int)((b >>  8) & 0xFF), (int)((t >>  8) & 0xFF), strength);
    int bl = tint_channel((int)( b        & 0xFF), (int)( t        & 0xFF), strength);

    return (jint) (((uint32_t)a << 24) | ((uint32_t)r << 16) |
                  ((uint32_t)g << 8)   |  (uint32_t)bl);
}