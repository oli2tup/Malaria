import keras
import pathlib
import tensorflow as tf

from keras import applications
from keras.applications import resnet
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, MaxPool2D
from keras.layers.convolutional import Conv2D

from tensorflow.keras.optimizers import SGD
from tensorflow.keras.preprocessing.image import ImageDataGenerator

import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

img_width = 224
img_height = 224

datagen = ImageDataGenerator(rescale=1/255.0, validation_split=0.2) # using 20% of data as validation

train_data_generator = datagen.flow_from_directory(
    directory ='/content/CE301/cell_images', target_size =(img_width, img_height),
    class_mode = 'binary',
    batch_size = 16,
    subset = 'training'
) # Training subset

validation_data_generator = datagen.flow_from_directory(
    directory ='/content/CE301/cell_images', target_size =(img_width, img_height),
    class_mode = 'binary',
    batch_size = 16,
    subset = 'validation'
) # Validation subset#

#model = keras.applications.resnet.ResNet50(weights="imagenet")
