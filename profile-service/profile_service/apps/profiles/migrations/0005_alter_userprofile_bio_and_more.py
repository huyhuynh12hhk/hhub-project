# Generated by Django 5.1.7 on 2025-05-17 01:21

from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ("profiles", "0004_alter_userprofile_email"),
    ]

    operations = [
        migrations.AlterField(
            model_name="userprofile",
            name="bio",
            field=models.CharField(blank=True, max_length=300),
        ),
        migrations.AlterField(
            model_name="userprofile",
            name="profile_cover",
            field=models.CharField(blank=True, max_length=1024),
        ),
        migrations.AlterField(
            model_name="userprofile",
            name="profile_picture",
            field=models.CharField(blank=True, max_length=1024),
        ),
    ]
